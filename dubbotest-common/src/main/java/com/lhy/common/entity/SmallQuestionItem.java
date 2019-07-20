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
 * @Description: 小程序驾考题库项目关联表
 * @author liuhaiyuan
 * @date 2019-07-20 18:57:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "small_question_item")
public class SmallQuestionItem implements Serializable {

    private static final long serialVersionUID = 9172610362546882936L;
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
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
	}
}
