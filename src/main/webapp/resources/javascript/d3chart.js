function temp(){
	var width = 1300,
	    height = 800;
	
	var xScale = d3.scale.linear()
    .domain([0,width]).range([0,width]);
    var yScale = d3.scale.linear()
    .domain([0,height]).range([0, height]);
	
	var color = d3.scale.category20();
	
	var force = d3.layout.force()
	    .charge(-3500)
	    .linkDistance(350)
	    .size([width, height]);
	
	var svg = d3.select("temp")
		.attr("tabindex", 1)
		.each(function() { this.focus(); })
		.append("svg")		
	    .attr("width", width)
	    .attr("height", height)
	    .attr("pointer-events", "all")
	    .append('svg:g')
	    .call(d3.behavior.zoom().on("zoom", redraw))
	    .append('svg:g');
	
	var zoomer = d3.behavior.zoom().
    scaleExtent([0.1,10]).
    x(xScale).
    y(yScale).
    on("zoomstart", zoomstart).
    on("zoom", redraw);

	function zoomstart() {
	    node.each(function(d) {
	        d.selected = false;
	        d.previouslySelected = false;
	    });
	    node.classed("selected", false);
	}
	
	function redraw() {
		svg.attr("transform",
	             "translate(" + d3.event.translate + ")" + " scale(" + d3.event.scale + ")");
	}
	
	
	d3.json("getChartJson", function(error, graph) {
		console.log(graph);
	  if (error) throw error;
	
	  force
	      .nodes(graph.nodes)
	      .links(graph.links)
	      .start();
	
	  var link = svg.selectAll(".link")
	      .data(graph.links)
	    .enter().append("line")
	      .attr("class", "link")
	      .style("stroke-width", function(d) { return d.value;});
	
	  var node = svg.selectAll(".node")
	      .data(graph.nodes)
	    .enter().append("circle")
	      .attr("class", "node")
	      .attr("r", 50)
	      .style("fill", function(d) { return color(d.group); })
	      .call(force.drag);
	  
	  var texts = svg.selectAll("text.label")
				  .data(graph.nodes)
				  .enter().append("text")
				  .attr("class", "label")
				  .attr("fill", "black")
				  .text(function(d) {
				      return d.name;
				  });
	  function dragstarted(d) {
          d3.event.sourceEvent.stopPropagation();
          if (!d.selected && !shiftKey) {
              // if this node isn't selected, then we have to unselect every other node
              node.classed("selected", function(p) { return p.selected =  p.previouslySelected = false; });
          }

          d3.select(this).classed("selected", function(p) { d.previouslySelected = d.selected; return d.selected = true; });

          node.filter(function(d) { return d.selected; })
          .each(function(d) { d.fixed |= 2; })
      }

      function dragged(d) {
          node.filter(function(d) { return d.selected; })
          .each(function(d) { 
              d.x += d3.event.dx;
              d.y += d3.event.dy;

              d.px += d3.event.dx;
              d.py += d3.event.dy;
          })

          force.resume();
      }
	  node.append("title")
	      .text(function(d) { return d.name; });
	
	  force.on("tick", function() {
	    link.attr("x1", function(d) { return d.source.x; })
	        .attr("y1", function(d) { return d.source.y; })
	        .attr("x2", function(d) { return d.target.x; })
	        .attr("y2", function(d) { return d.target.y; });
	
	    node.attr("cx", function(d) { return d.x; })
	        .attr("cy", function(d) { return d.y; });
	    
	    texts.attr("x", function(d) { return d.x; })
	      .attr("y", function(d) { return d.y; });
	  });
	});
}