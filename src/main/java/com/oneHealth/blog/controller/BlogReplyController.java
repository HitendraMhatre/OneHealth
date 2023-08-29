package com.oneHealth.blog.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneHealth.blog.Exception.BlogReplyNotFoundException;
import com.oneHealth.blog.entities.BlogReply;
import com.oneHealth.blog.services.BlogReplyService;
/**
 * The BlogReplyController class handles the API endpoints related to BlogReply operations.
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
@RequestMapping("/blog/")
public class BlogReplyController {

    @Autowired
    BlogReplyService service;

    /**
     * Retrieves all the BlogReplys.
     *
     * @return ResponseEntity with the list of BlogReply objects if they exist,
     *         or a no content response if no BlogReplys are found.
     */
    @GetMapping(value = "api/BlogReply")
    public ResponseEntity<List<BlogReply>> showBlogReplys() {
        List<BlogReply> BlogReplys = service.getBlogReplys();
        if (BlogReplys.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(BlogReplys);
        }
    }

    /**
     * Retrieves a specific BlogReply by its ID.
     *
     * @param deptId The ID of the BlogReply to retrieve.
     * @return ResponseEntity with the BlogReply if found,
     *         or throws BlogReplyNotFoundException if the BlogReply is not found.
     */
    @GetMapping(value = "api/BlogReplyById/{replyId}")
    public ResponseEntity<BlogReply> getBlogReply(@PathVariable int replyId) {
        try {
            Optional<BlogReply> BlogReply = service.getBlogReply(replyId);
            return BlogReply.map(ResponseEntity::ok).orElseThrow(() ->
                    new BlogReplyNotFoundException("BlogReply not found with ID: " + replyId));
        } catch (BlogReplyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    
    /**
     * Retrieves a specific BlogReply by its Comment ID.
     *
     * @param ComentID The ID comments whose the BlogReply to retrieve.
     * @return ResponseEntity with the BlogReply if found,
     *         or throws BlogReplyNotFoundException if the BlogReply is not found.
     */
    @GetMapping(value = "api/ReplyByCommentId/{commentId}")
    public ResponseEntity<List<BlogReply>> getBlogReplyByCommentId(@PathVariable int commentId) {
        try {
            List<BlogReply> blogReplies = service.getBlogReplyByCommentId(commentId);
            if (!blogReplies.isEmpty()) {
                return ResponseEntity.ok(blogReplies);
            } else {
                throw new BlogReplyNotFoundException("BlogReplies not found for comment ID: " + commentId);
            }
        } catch (BlogReplyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }



    /**
     * Removes a BlogReply by its ID.
     *
     * @param deptId The ID of the BlogReply to remove.
     * @return ResponseEntity with a success message if the BlogReply is deleted successfully,
     *         or an error message if the BlogReply deletion fails.
     */
    @DeleteMapping(value = "api/BlogReply/{replyId}")
    public ResponseEntity<String> removeBlogReply(@PathVariable int replyId) {
        try {
            service.delete(replyId);
            return ResponseEntity.ok("BlogReply deleted successfully.");
        } catch (BlogReplyNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("BlogReply not found with ID: " + replyId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete BlogReply: " + e.getMessage());
        }
    }

    /**
     * Adds a new BlogReply.
     *
     * @param BlogReply The BlogReply object to add.
     * @return ResponseEntity with a success message if the BlogReply is added successfully,
     *         or an error message if the BlogReply addition fails.
     */
    @PostMapping(value = "api/BlogReply")
    public ResponseEntity<String> addBlogReply(@RequestBody BlogReply BlogReply) {
        try {
        	// Set the registration timestamp to the current time
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            BlogReply.setCreationTimeStamp(currentTimestamp);
            BlogReply.setEditedTimeStamp(currentTimestamp);
            service.addBlogReply(BlogReply);
            return ResponseEntity.ok("BlogReply added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add BlogReply: " + e.getMessage());
        }
    }

    /**
     * Updates an existing BlogReply by its ID.
     *
     * @param BlogReply The updated BlogReply object.
     * @param deptId     The ID of the BlogReply to update.
     * @return ResponseEntity with a success message if the BlogReply is updated successfully,
     *         or an error message if the BlogReply update fails.
     */
    @PutMapping(value = "api/BlogReply/{replyId}")
    public ResponseEntity<String> updateBlogReply(@RequestBody BlogReply BlogReply, @PathVariable int replyId) {
        try {
            service.update(BlogReply, replyId);
            return ResponseEntity.ok("BlogReply details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify BlogReply details: " + e.getMessage());
        }
    }
}

