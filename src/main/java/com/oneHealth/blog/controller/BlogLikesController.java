package com.oneHealth.blog.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneHealth.blog.Exception.BlogLikesNotFoundException;
import com.oneHealth.blog.entities.BlogLikes;
import com.oneHealth.blog.services.BlogLikesService;

/**
 * The BlogLikesController class handles the API endpoints related to BlogLikes operations.
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
@RequestMapping("/blog/")
public class BlogLikesController {

    @Autowired
    BlogLikesService service;

    /**
     * Retrieves all the BlogLikess.
     *
     * @return ResponseEntity with the list of BlogLikes objects if they exist,
     *         or a no content response if no BlogLikess are found.
     */
    @GetMapping(value = "api/Likes")
    public ResponseEntity<List<BlogLikes>> showBlogLikess() {
        List<BlogLikes> BlogLikess = service.getBlogLikess();
        if (BlogLikess.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(BlogLikess);
        }
    }

    /**
     * Retrieves a specific BlogLikes by its ID.
     *
     * @param deptId The ID of the BlogLikes to retrieve.
     * @return ResponseEntity with the BlogLikes if found,
     *         or throws BlogLikesNotFoundException if the BlogLikes is not found.
     */
    @GetMapping(value = "api/BlogLikesById/{deptId}")
    public ResponseEntity<BlogLikes> getBlogLikes(@PathVariable int deptId) {
        try {
            Optional<BlogLikes> BlogLikes = service.getBlogLikes(deptId);
            return BlogLikes.map(ResponseEntity::ok).orElseThrow(() ->
                    new BlogLikesNotFoundException("BlogLikes not found with ID: " + deptId));
        } catch (BlogLikesNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Retrieves a specific BlogLikes by its ID.
     *
     * @param deptId The ID of the BlogLikes to retrieve.
     * @return ResponseEntity with the BlogLikes if found,
     *         or throws BlogLikesNotFoundException if the BlogLikes is not found.
     */
    
    @GetMapping(value = "api/BlogLikesByUserId/{userId}/{postId}")
    public ResponseEntity<List<BlogLikes>> getBlogLikesByUID(@PathVariable int userId, @PathVariable int postId) {
        try {
            List<BlogLikes> blogLikess = service.getPostLikesByUID(userId,postId);
            if (blogLikess.isEmpty()) {
                throw new BlogLikesNotFoundException("No BlogLikess found with User ID: " + userId +" and Post ID:" + postId);
            }
            return ResponseEntity.ok(blogLikess);
        } catch (BlogLikesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping(value = "api/LikesCount/{postId}")
    public ResponseEntity<Long> getLikesByPID(@PathVariable int postId) {
        try {
            Long avgBlogLikes = service.getLikesCount(true, postId);
            
            if (avgBlogLikes != null) {
                return ResponseEntity.ok(avgBlogLikes);
            } else {
                throw new BlogLikesNotFoundException("BlogLikes not found with ID: " + postId);
            }
        } catch (BlogLikesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping(value = "api/UnlikesCount/{postId}")
    public ResponseEntity<Long> getUnlikesByPID(@PathVariable int postId) {
        try {
            Long avgBlogLikes = service.getLikesCount(false, postId);
            
            if (avgBlogLikes != null) {
                return ResponseEntity.ok(avgBlogLikes);
            } else {
                throw new BlogLikesNotFoundException("BlogLikes not found with ID: " + postId);
            }
        } catch (BlogLikesNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /**
     * Removes a BlogLikes by its ID.
     *
     * @param deptId The ID of the BlogLikes to remove.
     * @return ResponseEntity with a success message if the BlogLikes is deleted successfully,
     *         or an error message if the BlogLikes deletion fails.
     */
    @DeleteMapping(value = "api/BlogLikes/{LikesId}")
    public ResponseEntity<String> removeBlogLikes(@PathVariable int LikesId) {
        try {
            service.delete(LikesId);
            return ResponseEntity.ok("BlogLikes deleted successfully.");
        } catch (BlogLikesNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("BlogLikes not found with ID: " + LikesId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete BlogLikes: " + e.getMessage());
        }
    }

    /**
     * Adds a new BlogLikes.
     *
     * @param BlogLikes The BlogLikes object to add.
     * @return ResponseEntity with a success message if the BlogLikes is added successfully,
     *         or an error message if the BlogLikes addition fails.
     */
//    @PostMapping(value = "api/Likes")
//    public ResponseEntity<String> addBlogLikes(@RequestBody BlogLikes BlogLikes) {
//        try {
//            service.addBlogLikes(BlogLikes);
//            return ResponseEntity.ok("BlogLikes added successfully.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to add BlogLikes: " + e.getMessage());
//        }
//    }

    @PostMapping(value = "api/Likes")
    public ResponseEntity<String> addOrUpdateBlogLikes(@RequestBody BlogLikes blogLikes) {
        try {
            // Check if a like with the same combination of type, typeId, and userId exists
            BlogLikes existingLike = service.getBlogLikesByPostIDAndUserId(
                blogLikes.getPost().getBlogPostId(),
                blogLikes.getLikesId(),
                blogLikes.getUser().getBlogUserId(),
                blogLikes.isFlag(),
                blogLikes.isFunction()
            );            
            if (existingLike != null) {
                // If like exists, update it using PUT method logic
                service.updateBlogLikes(existingLike, blogLikes);
                return ResponseEntity.ok("BlogLikes updated successfully.");
            } else {
                // If like doesn't exist, add a new like using POST method logic
                service.addBlogLikes(blogLikes);
                return ResponseEntity.ok("BlogLikes added successfully.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add/update BlogLikes: " + e.getMessage());
        }
    }
    
    
    @GetMapping(value = "api/BlogUserIdByPostId/{postId}")
    public ResponseEntity<List<Integer>> showBloguser(@PathVariable int postId) {
        List<Integer> blogFavourite = service.getUserIdByPostId(postId);
        if (blogFavourite.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(blogFavourite);
        }
    }


    
}

