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

import com.oneHealth.blog.Exception.BlogCommentsNotFoundException;
import com.oneHealth.blog.entities.BlogComments;
import com.oneHealth.blog.services.BlogCommentsService;
/**
 * The BlogCommentsController class handles the API endpoints related to BlogComments operations.
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
@RequestMapping("/blog/")
public class BlogCommentsController {

    @Autowired
    BlogCommentsService service;

    /**
     * Retrieves all the BlogCommentss.
     *
     * @return ResponseEntity with the list of BlogComments objects if they exist,
     *         or a no content response if no BlogCommentss are found.
     */
    @GetMapping(value = "api/BlogComments")
    public ResponseEntity<List<BlogComments>> showBlogCommentss() {
        List<BlogComments> BlogCommentss = service.getBlogCommentss();
        if (BlogCommentss.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(BlogCommentss);
        }
    }

    /**
     * Retrieves a specific BlogComments by its ID.
     *
     * @param deptId The ID of the BlogComments to retrieve.
     * @return ResponseEntity with the BlogComments if found,
     *         or throws BlogCommentsNotFoundException if the BlogComments is not found.
     */
    @GetMapping(value = "api/BlogCommentsById/{deptId}")
    public ResponseEntity<BlogComments> getBlogComments(@PathVariable int deptId) {
        try {
            Optional<BlogComments> BlogComments = service.getBlogComments(deptId);
            return BlogComments.map(ResponseEntity::ok).orElseThrow(() ->
                    new BlogCommentsNotFoundException("BlogComments not found with ID: " + deptId));
        } catch (BlogCommentsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

  @GetMapping(value = "api/AvgBlogRatingByPostId/{postId}")
  public ResponseEntity<Double> getAvgBlogRatingByPID(@PathVariable int postId) {
      try {
          Double avgBlogRating = service.getPostAverageRating(postId);
          
          if (avgBlogRating != null) {
              return ResponseEntity.ok(avgBlogRating);
          } else {
              throw new BlogCommentsNotFoundException("BlogComment not found with ID: " + postId);
          }
      } catch (BlogCommentsNotFoundException e) {
          return ResponseEntity.notFound().build();
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
  }

    /**
     * Removes a BlogComments by its ID.
     *
     * @param deptId The ID of the BlogComments to remove.
     * @return ResponseEntity with a success message if the BlogComments is deleted successfully,
     *         or an error message if the BlogComments deletion fails.
     */
    @DeleteMapping(value = "api/BlogComments/{deptId}")
    public ResponseEntity<String> removeBlogComments(@PathVariable int deptId) {
        try {
            service.delete(deptId);
            return ResponseEntity.ok("BlogComments deleted successfully.");
        } catch (BlogCommentsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("BlogComments not found with ID: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete BlogComments: " + e.getMessage());
        }
    }

    /**
     * Adds a new BlogComments.
     *
     * @param BlogComments The BlogComments object to add.
     * @return ResponseEntity with a success message if the BlogComments is added successfully,
     *         or an error message if the BlogComments addition fails.
     */
//    @PostMapping(value = "api/BlogComments")
//    public ResponseEntity<String> addBlogComments(@RequestBody BlogComments BlogComments) {
//        try {
//        	// Set the registration timestamp to the current time
//            Timestamp currentTimestamp = Timestamp.from(Instant.now());
//            BlogComments.setCreationTimeStamp(currentTimestamp);
//            BlogComments.setEditedTimeStamp(currentTimestamp);
//            service.addBlogComments(BlogComments);
//            return ResponseEntity.ok("BlogComments added successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to add BlogComments: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Updates an existing BlogComments by its ID.
//     *
//     * @param BlogComments The updated BlogComments object.
//     * @param deptId     The ID of the BlogComments to update.
//     * @return ResponseEntity with a success message if the BlogComments is updated successfully,
//     *         or an error message if the BlogComments update fails.
//     */
//    @PutMapping(value = "api/BlogComments/{deptId}")
//    public ResponseEntity<String> updateBlogComments(@RequestBody BlogComments BlogComments, @PathVariable int deptId) {
//        try {
//            service.update(BlogComments, deptId);
//            return ResponseEntity.ok("BlogComments details modified successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to modify BlogComments details: " + e.getMessage());
//        }
//    }
    @PostMapping(value = "api/BlogComments")
    public ResponseEntity<String> addBlogComments(@RequestBody BlogComments blogComment) {
        try {
            // Check if the user has already commented on the post
            boolean userAlreadyCommented = service.userAlreadyCommented(blogComment.getUser(), blogComment.getPost());
            if (userAlreadyCommented) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("You can only comment once on this post.");
            }
            
            // Set the registration timestamp to the current time
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            blogComment.setCreationTimeStamp(currentTimestamp);
            blogComment.setEditedTimeStamp(currentTimestamp);
            
            service.addBlogComments(blogComment);
            return ResponseEntity.ok("BlogComments added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add BlogComments: " + e.getMessage());
        }
    }

    @PutMapping(value = "api/BlogComments/{deptId}")
    public ResponseEntity<String> updateBlogComments(@RequestBody BlogComments updatedComment, @PathVariable int deptId) {
        try {
            // Check if the comment exists and belongs to the user
            Optional<BlogComments> existingCommentOptional = service.getBlogComments(deptId);
            
            if (existingCommentOptional.isEmpty() || 
                !existingCommentOptional.get().getUser().equals(updatedComment.getUser())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Comment does not exist or does not belong to the user.");
            }
            
            // Update the comment details
            service.update(updatedComment, deptId);
            return ResponseEntity.ok("BlogComments details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify BlogComments details: " + e.getMessage());
        }
    }
    
  /**
  * Retrieves a specific BlogComments by its ID.
  *
  * @param deptId The ID of the BlogComments to retrieve.
  * @return ResponseEntity with the BlogComments if found,
  *         or throws BlogCommentsNotFoundException if the BlogComments is not found.
  */
    @GetMapping(value = "api/BlogCommentsByPostId/{postId}")
    public ResponseEntity<List<BlogComments>> getBlogCommentsByPID(@PathVariable int postId) {
        try {
            List<BlogComments> blogComments = service.getBlogCommentsByPID(postId);
            if (!blogComments.isEmpty()) {
                return ResponseEntity.ok(blogComments);
            } else {
                throw new BlogCommentsNotFoundException("BlogComments not found with Post ID: " + postId);
            }
        } catch (BlogCommentsNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}

