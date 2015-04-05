package com.healthcare.freemedicalopinion.entityobject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.TextScore;

import com.healthcare.freemedicalopinion.utility.ContentStatusEnum.ContentStatus;
import com.healthcare.freemedicalopinion.valueobject.ContentVO;

@Document(collection = "content")
public class ContentEO {

	public ContentEO() {

	}

	public ContentEO(ContentVO vo) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		this.categoryId = vo.getCategoryId();
		this.contentBody = vo.getContentBody();
		this.contentId = vo.getContentId();
		this.imageName = vo.getImageName();
		this.status = vo.getStatus();
		this.subCategoryId = vo.getSubCategoryId();
		this.title = vo.getTitle();
		if (vo.getUpdatedDate() != null) {
			this.updatedDate = fmt.parse(vo.getUpdatedDate());
		}

		this.author = vo.getAuthor();
		this.comment = vo.getComment();
		if (vo.getTags() != null) {

			this.tags = new HashSet<String>(Arrays.asList(vo.getTags().split(
					",")));
			this.tags.remove("");

		}

	}

	@Id
	private String id;
	private String contentId;
	private String categoryId;
	private List<String> subCategoryId;
	@TextIndexed
	private String title;
	private String imageName;
	private String contentBody;
	private ContentStatus status;
	private Date updatedDate;
	@TextIndexed
	private Set<String> tags;
	private String author;
	private String comment;
	private String parentContentId;
	@TextScore
	private Float score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the subCategoryId
	 */
	public List<String> getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * @param subCategoryId
	 *            the subCategoryId to set
	 */
	public void setSubCategoryId(List<String> subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getContentBody() {
		return contentBody;
	}

	public void setContentBody(String contentBody) {
		this.contentBody = contentBody;
	}

	public ContentStatus getStatus() {
		return status;
	}

	public void setStatus(ContentStatus status) {
		this.status = status;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the parentContentId
	 */
	public String getParentContentId() {
		return parentContentId;
	}

	/**
	 * @param parentContentId
	 *            the parentContentId to set
	 */
	public void setParentContentId(String parentContentId) {
		this.parentContentId = parentContentId;
	}

}
