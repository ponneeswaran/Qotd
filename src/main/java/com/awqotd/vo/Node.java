/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class Node {
  private String tagName;
	private String url;
	private int id;
	
	public String getTagName()
	{
		return this.tagName;
	}
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}
	
	public String getUrl()
	{
		return this.url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public int getId()
	{
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
}
