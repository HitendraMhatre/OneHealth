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

import com.oneHealth.blog.Exception.BlogFavouriteNotFoundException;
import com.oneHealth.blog.entities.BlogFavourite;
import com.oneHealth.blog.entities.BlogUser;
import com.oneHealth.blog.services.BlogFavouriteService;

/**
 * The BlogFavouriteController class handles the API endpoints related to BlogFavourite operations.
 */
@RestController
//@CrossOrigin(origins = {"http://localhost:3000", "http://onestop-fe-hitendra7-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com"})
@RequestMapping("/blog/")
public class BlogFavouriteController {  

    @Autowired
    BlogFavouriteService service;

    /**
     * Retrieves all the BlogFavourites.
     *
     * @return ResponseEntity with the list of BlogFavourite objects if they exist,
     *         or a no content response if no BlogFavourites are found.
     */
    @GetMapping(value = "api/BlogFavourite")
    public ResponseEntity<List<BlogFavourite>> showBlogFavourites() {
        List<BlogFavourite> BlogFavourites = service.getBlogFavourites();
        if (BlogFavourites.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(BlogFavourites);
        }
    }

    /**
     * Retrieves a specific BlogFavourite by its ID.
     *
     * @param deptId The ID of the BlogFavourite to retrieve.
     * @return ResponseEntity with the BlogFavourite if found,
     *         or throws BlogFavouriteNotFoundException if the BlogFavourite is not found.
     */
    @GetMapping(value = "api/BlogFavouriteById/{deptId}")
    public ResponseEntity<BlogFavourite> getBlogFavourite(@PathVariable int deptId) {
        try {
            Optional<BlogFavourite> BlogFavourite = service.getBlogFavourite(deptId);
            return BlogFavourite.map(ResponseEntity::ok).orElseThrow(() ->
                    new BlogFavouriteNotFoundException("BlogFavourite not found with ID: " + deptId));
        } catch (BlogFavouriteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Retrieves a specific BlogFavourite by its ID.
     *
     * @param deptId The ID of the BlogFavourite to retrieve.
     * @return ResponseEntity with the BlogFavourite if found,
     *         or throws BlogFavouriteNotFoundException if the BlogFavourite is not found.
     */
    
    @GetMapping(value = "api/BlogFavouriteByUserId/{userId}")
    public ResponseEntity<List<BlogFavourite>> getBlogFavouriteByUID(@PathVariable int userId) {
        try {
            List<BlogFavourite> blogFavourites = service.getBlogFavouriteByUID(userId);
            if (blogFavourites.isEmpty()) {
                throw new BlogFavouriteNotFoundException("No BlogFavourites found with User ID: " + userId);
            }
            return ResponseEntity.ok(blogFavourites);
        } catch (BlogFavouriteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(value = "api/BlogFavouriteCount/{postId}")
    public ResponseEntity<Long> getAvgBlogFavouriteByPID(@PathVariable int postId) {
        try {
            Long avgBlogFavourite = service.getFavouriteCount(postId);
            
            if (avgBlogFavourite != null) {
                return ResponseEntity.ok(avgBlogFavourite);
            } else {
                throw new BlogFavouriteNotFoundException("BlogFavourite not found with ID: " + postId);
            }
        } catch (BlogFavouriteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    /**
     * Removes a BlogFavourite by its ID.
     *
     * @param deptId The ID of the BlogFavourite to remove.
     * @return ResponseEntity with a success message if the BlogFavourite is deleted successfully,
     *         or an error message if the BlogFavourite deletion fails.
     */
    @DeleteMapping(value = "api/BlogFavourite/{FavouriteId}")
    public ResponseEntity<String> removeBlogFavourite(@PathVariable int FavouriteId) {
        try {
            service.delete(FavouriteId);
            return ResponseEntity.ok("BlogFavourite deleted successfully.");
        } catch (BlogFavouriteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("BlogFavourite not found with ID: " + FavouriteId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete BlogFavourite: " + e.getMessage());
        }
    }

    /**
     * Adds a new BlogFavourite.
     *
     * @param BlogFavourite The BlogFavourite object to add.
     * @return ResponseEntity with a success message if the BlogFavourite is added successfully,
     *         or an error message if the BlogFavourite addition fails.
     */
    @PostMapping(value = "api/BlogFavourite")
    public ResponseEntity<String> addBlogFavourite(@RequestBody BlogFavourite BlogFavourite) {
        try {
            service.addBlogFavourite(BlogFavourite);
            return ResponseEntity.ok("BlogFavourite added successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add BlogFavourite: " + e.getMessage());
        }
    }
    
    @GetMapping(value = "api/BloguserIdByPostId/{postId}")
    public ResponseEntity<List<Integer>> showBloguser(@PathVariable int postId) {
        List<Integer> blogFavourite = service.getUserIdByPostId(postId);
        if (blogFavourite.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(blogFavourite);
        }
    }

    
}

