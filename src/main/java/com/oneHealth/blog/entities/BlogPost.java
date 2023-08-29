package com.oneHealth.blog.entities;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_post_id")
    private Long blogPostId;

    @Column(name = "approval_status", nullable = false)
    private boolean approvalStatus = false; // Default value is "Not Approved"

    @ManyToOne
    @JoinColumn(name = "approved_by", referencedColumnName = "blog_user_id")
    private BlogUser approvedBy;
    
	
	@Column(name = "title")
    private String title;

    @Lob
    private String content;
    
    @Column(name = "image_path")
    private String imagePath;
    
    @Column(name = "post_time_stamp")
    private Timestamp postTimeStamp; 

    @Nonnull
    @ManyToOne
    @JoinColumn(name = "blog_user_id", referencedColumnName = "blog_user_id", nullable = false)
    private BlogUser user;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "edited_time_stamp", nullable = false, updatable = false)
    private Timestamp editedTimeStamp;
    
    
    @Column(name = "department_id")
    private int departmentId;
    
    public Timestamp getEditedTimeStamp() {
		return editedTimeStamp;
	}

	public void setEditedTimeStamp(Timestamp editedTimeStamp) {
		this.editedTimeStamp = editedTimeStamp;
	}

//	public Set<BlogComments> getComments() {
//		return comments;
//	}
//
//	public void setComments(Set<BlogComments> comments) {
//		this.comments = comments;
//	}
	
//	@JsonIgnoreProperties("comments")
//	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private Set<BlogComments> comments;

	public String getImagePath() {
		return imagePath;
	}

	public Timestamp getBlogEditedDate() {
		return editedTimeStamp;
	}

	public void setBlogEditedDate(Timestamp blogEditedDate) {
		this.editedTimeStamp = blogEditedDate;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public BlogUser getUser() {
		return user;
	}

	public void setUser(BlogUser user) {
		this.user = user;
	}

	public void setBlogPostId(Long blogPostId) {
		this.blogPostId = blogPostId;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Timestamp getPostTimeStamp() {
		return postTimeStamp;
	}

	public void setPostTimeStamp(Timestamp postTimeStamp) {
		this.postTimeStamp = postTimeStamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	 public boolean getApprovalStatus() {
	        return approvalStatus;
	    }

	    public void setApprovalStatus(boolean approvalStatus) {
	        this.approvalStatus = approvalStatus;
	    }

	    public BlogUser getApprovedBy() {
	        return approvedBy;
	    }

	    public void setApprovedBy(BlogUser approvedBy) {
	        this.approvedBy = approvedBy;
	    }
	public BlogPost(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public BlogPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogPost(boolean approvalStatus, BlogUser approvedBy, String title, String content, String imagePath,
			Timestamp postTimeStamp, BlogUser user, Timestamp editedTimeStamp, int departmentId) {
		super();
		this.approvalStatus = approvalStatus;
		this.approvedBy = approvedBy;
		this.title = title;
		this.content = content;
		this.imagePath = imagePath;
		this.postTimeStamp = postTimeStamp;
		this.user = user;
		this.editedTimeStamp = editedTimeStamp;
		this.departmentId = departmentId;
	}

	public Long getBlogPostId() {
		return blogPostId;
	}

}
