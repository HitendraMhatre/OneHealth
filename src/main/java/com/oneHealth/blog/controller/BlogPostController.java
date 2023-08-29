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

//import com.oneHealth.blog.DTO.BlogPostWithRatingDTO;
import com.oneHealth.blog.Exception.BlogPostNotFoundException;
import com.oneHealth.blog.entities.BlogPost;
import com.oneHealth.blog.services.BlogPostService;
/**
 * The BlogPostController class handles the API endpoints related to BlogPost operations.
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
@RequestMapping("/blog/")
public class BlogPostController {

    @Autowired
    BlogPostService service;

    /**
     * Retrieves all the BlogPosts.
     *
     * @return ResponseEntity with the list of BlogPost objects if they exist,
     *         or a no content response if no BlogPosts are found.
     */
    @GetMapping(value = "api/BlogPost")
    public ResponseEntity<List<BlogPost>> showBlogPosts() {
        List<BlogPost> BlogPosts = service.getBlogPosts();
        if (BlogPosts.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(BlogPosts);
        }
    }

    /**
     * Retrieves a specific BlogPost by its ID.
     *
     * @param deptId The ID of the BlogPost to retrieve.
     * @return ResponseEntity with the BlogPost if found,
     *         or throws BlogPostNotFoundException if the BlogPost is not found.
     */
    @GetMapping(value = "api/BlogPostById/{deptId}")
    public ResponseEntity<BlogPost> getBlogPost(@PathVariable int deptId) {
        try {
            Optional<BlogPost> BlogPostWithRatingDTO = service.getBlogPost(deptId);
            return BlogPostWithRatingDTO.map(ResponseEntity::ok).orElseThrow(() ->
                    new BlogPostNotFoundException("BlogPost not found with ID: " + deptId));
        } catch (BlogPostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(null);
        }
    }

    /**
     * Removes a BlogPost by its ID.
     *
     * @param deptId The ID of the BlogPost to remove.
     * @return ResponseEntity with a success message if the BlogPost is deleted successfully,
     *         or an error message if the BlogPost deletion fails.
     */
    @DeleteMapping(value = "api/BlogPost/{deptId}")
    public ResponseEntity<String> removeBlogPost(@PathVariable int deptId) {
        try {
            service.delete(deptId);
            return ResponseEntity.ok("BlogPost deleted successfully.");
        } catch (BlogPostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("BlogPost not found with ID: " + deptId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete BlogPost: " + e.getMessage());
        }
    }

    /**
     * Adds a new BlogPost.
     *
     * @param BlogPost The BlogPost object to add.
     * @return ResponseEntity with a success message if the BlogPost is added successfully,
     *         or an error message if the BlogPost addition fails.
     */
    @PostMapping(value = "api/BlogPost")
    public ResponseEntity<String> addBlogPost(@RequestBody BlogPost BlogPost) {
        try {
        	// Set the registration timestamp to the current time
            Timestamp currentTimestamp = Timestamp.from(Instant.now());
            BlogPost.setPostTimeStamp(currentTimestamp);
            BlogPost.setBlogEditedDate(currentTimestamp);
            service.addBlogPost(BlogPost);
            return ResponseEntity.ok("BlogPost added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add BlogPost: " + e.getMessage());
        }
    }

    /**
     * Updates an existing BlogPost by its ID.
     *
     * @param BlogPost The updated BlogPost object.
     * @param deptId     The ID of the BlogPost to update.
     * @return ResponseEntity with a success message if the BlogPost is updated successfully,
     *         or an error message if the BlogPost update fails.
     */
    @PutMapping(value = "api/BlogPost/{deptId}")
    public ResponseEntity<String> updateBlogPost(@RequestBody BlogPost BlogPost, @PathVariable int deptId) {
        try {
            service.update(BlogPost, deptId);
            return ResponseEntity.ok("BlogPost details modified successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to modify BlogPost details: " + e.getMessage());
        }
    }
}

