/**
 * 
 */
package com.awqotd.vo;

/**
 * @author Ponneeswaran
 *
 */
public class OptionsVO {
	private int correctness;
	private String optTag;
	private String optionText;
	/**
	 * @return the correctness
	 */
	public int getCorrectness() {
		return correctness;
	}
	/**
	 * @param correctness the correctness to set
	 */
	public void setCorrectness(int correctness) {
		this.correctness = correctness;
	}
	/**
	 * @return the optTag
	 */
	public String getOptTag() {
		return optTag;
	}
	/**
	 * @param optTag the optTag to set
	 */
	public void setOptTag(String optTag) {
		this.optTag = optTag;
	}
	/**
	 * @return the optionText
	 */
	public String getOptionText() {
		return optionText;
	}
	/**
	 * @param optionText the optionText to set
	 */
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
}
