package com.lhy.common.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.SequenceGenerator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**   
 * @Title: Entity
 * @Description: 小程序驾考题库考题明细表
 * @author liuhaiyuan
 * @date 2019-07-19 09:46:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "small_question_item")
public class SmallQuestionItem implements Serializable {

    private static final long serialVersionUID = 1627700427117211320L;
	/***/
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Long id;
	/**创建时间*/
	@Column(name ="create_at")
	private Long createAt;
	/**更新时间*/
	@Column(name ="update_at")
	private Long updateAt;
	/**科目类型1/4*/
	@Column(name ="type")
	private Integer type;
	/**城市id*/
	@Column(name ="city_id")
	private Long cityId;
	/**驾校id*/
	@Column(name ="service_id")
	private Long serviceId;
	/**栏目类型*/
	@Column(name ="types")
	private Integer types;
	/***/
	@Column(name ="md5")
	private String md5;
	/***/
	@Column(name ="bank_id")
	private Long bankId;
	/***/
	@Column(name ="pid")
	private Integer pid;
	/***/
	@Column(name ="classify")
	private Integer classify;
	/***/
	@Column(name ="mt_type")
	private Integer mtType;
	/***/
	@Column(name ="content_img")
	private String contentImg;
	/***/
	@Column(name ="content")
	private String content;
	/***/
	@Column(name ="item1")
	private String item1;
	/***/
	@Column(name ="item2")
	private String item2;
	/***/
	@Column(name ="item3")
	private String item3;
	/***/
	@Column(name ="item4")
	private String item4;
	/***/
	@Column(name ="item_true")
	private Integer itemTrue;
	/***/
	@Column(name ="audio")
	private String audio;
	/***/
	@Column(name ="key_words")
	private String keyWords;
	/***/
	@Column(name ="sort1")
	private Integer sort1;
	/***/
	@Column(name ="sort2")
	private Integer sort2;
	/***/
	@Column(name ="explanation")
	private String explanation;
	/***/
	@Column(name ="jkbd_id")
	private Integer jkbdId;
	/***/
	@Column(name ="item_true_text")
	private String itemTrueText;
	/***/
	@Column(name ="key_topic")
	private String keyTopic;
	/***/
	@Column(name ="key_answer")
	private String keyAnswer;
	/***/
	@Column(name ="explanationgif")
	private String explanationgif;
	/***/
	@Column(name ="chapterid")
	private Integer chapterid;
	/***/
	@Column(name ="explain")
	private String explain;
	/***/
	@Column(name ="optiontype")
	private Integer optiontype;
	/***/
	@Column(name ="explanation1")
	private String explanation1;
	
	/**
	 *方法: 取得Long
	 *@return: Long  
	 */
	public Long getId(){
		return this.id;
	}

	/**
	 *方法: 设置Long
	 *@param: Long  
	 */
	public void setId(Long id){
		this.id = id;
	}
	/**
	 *方法: 取得Long
	 *@return: Long  创建时间
	 */
	public Long getCreateAt(){
		return this.createAt;
	}

	/**
	 *方法: 设置Long
	 *@param: Long  创建时间
	 */
	public void setCreateAt(Long createAt){
		this.createAt = createAt;
	}
	/**
	 *方法: 取得Long
	 *@return: Long  更新时间
	 */
	public Long getUpdateAt(){
		return this.updateAt;
	}

	/**
	 *方法: 设置Long
	 *@param: Long  更新时间
	 */
	public void setUpdateAt(Long updateAt){
		this.updateAt = updateAt;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  科目类型1/4
	 */
	public Integer getType(){
		return this.type;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  科目类型1/4
	 */
	public void setType(Integer type){
		this.type = type;
	}
	/**
	 *方法: 取得Long
	 *@return: Long  城市id
	 */
	public Long getCityId(){
		return this.cityId;
	}

	/**
	 *方法: 设置Long
	 *@param: Long  城市id
	 */
	public void setCityId(Long cityId){
		this.cityId = cityId;
	}
	/**
	 *方法: 取得Long
	 *@return: Long  驾校id
	 */
	public Long getServiceId(){
		return this.serviceId;
	}

	/**
	 *方法: 设置Long
	 *@param: Long  驾校id
	 */
	public void setServiceId(Long serviceId){
		this.serviceId = serviceId;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  栏目类型
	 */
	public Integer getTypes(){
		return this.types;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  栏目类型
	 */
	public void setTypes(Integer types){
		this.types = types;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getMd5(){
		return this.md5;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setMd5(String md5){
		this.md5 = md5;
	}
	/**
	 *方法: 取得Long
	 *@return: Long  
	 */
	public Long getBankId(){
		return this.bankId;
	}

	/**
	 *方法: 设置Long
	 *@param: Long  
	 */
	public void setBankId(Long bankId){
		this.bankId = bankId;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getPid(){
		return this.pid;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setPid(Integer pid){
		this.pid = pid;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getClassify(){
		return this.classify;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setClassify(Integer classify){
		this.classify = classify;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getMtType(){
		return this.mtType;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setMtType(Integer mtType){
		this.mtType = mtType;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getContentImg(){
		return this.contentImg;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setContentImg(String contentImg){
		this.contentImg = contentImg;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getContent(){
		return this.content;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setContent(String content){
		this.content = content;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getItem1(){
		return this.item1;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setItem1(String item1){
		this.item1 = item1;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getItem2(){
		return this.item2;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setItem2(String item2){
		this.item2 = item2;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getItem3(){
		return this.item3;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setItem3(String item3){
		this.item3 = item3;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getItem4(){
		return this.item4;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setItem4(String item4){
		this.item4 = item4;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getItemTrue(){
		return this.itemTrue;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setItemTrue(Integer itemTrue){
		this.itemTrue = itemTrue;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getAudio(){
		return this.audio;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setAudio(String audio){
		this.audio = audio;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getKeyWords(){
		return this.keyWords;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setKeyWords(String keyWords){
		this.keyWords = keyWords;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getSort1(){
		return this.sort1;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setSort1(Integer sort1){
		this.sort1 = sort1;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getSort2(){
		return this.sort2;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setSort2(Integer sort2){
		this.sort2 = sort2;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getExplanation(){
		return this.explanation;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setExplanation(String explanation){
		this.explanation = explanation;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getJkbdId(){
		return this.jkbdId;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setJkbdId(Integer jkbdId){
		this.jkbdId = jkbdId;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getItemTrueText(){
		return this.itemTrueText;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setItemTrueText(String itemTrueText){
		this.itemTrueText = itemTrueText;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getKeyTopic(){
		return this.keyTopic;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setKeyTopic(String keyTopic){
		this.keyTopic = keyTopic;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getKeyAnswer(){
		return this.keyAnswer;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setKeyAnswer(String keyAnswer){
		this.keyAnswer = keyAnswer;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getExplanationgif(){
		return this.explanationgif;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setExplanationgif(String explanationgif){
		this.explanationgif = explanationgif;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getChapterid(){
		return this.chapterid;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setChapterid(Integer chapterid){
		this.chapterid = chapterid;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getExplain(){
		return this.explain;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setExplain(String explain){
		this.explain = explain;
	}
	/**
	 *方法: 取得Integer
	 *@return: Integer  
	 */
	public Integer getOptiontype(){
		return this.optiontype;
	}

	/**
	 *方法: 设置Integer
	 *@param: Integer  
	 */
	public void setOptiontype(Integer optiontype){
		this.optiontype = optiontype;
	}
	/**
	 *方法: 取得String
	 *@return: String  
	 */
	public String getExplanation1(){
		return this.explanation1;
	}

	/**
	 *方法: 设置String
	 *@param: String  
	 */
	public void setExplanation1(String explanation1){
		this.explanation1 = explanation1;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
