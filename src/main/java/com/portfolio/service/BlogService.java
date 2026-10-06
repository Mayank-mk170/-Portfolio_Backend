package com.portfolio.service;

import com.portfolio.dto.BlogRequest;
import com.portfolio.entity.Blog;
import com.portfolio.repository.BlogRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    // CREATE
    public Blog createBlog(BlogRequest request) {

        Blog blog = new Blog();

        blog.setTitle(request.getTitle());
        blog.setSlug(request.getSlug());
        blog.setContent(request.getContent());
        blog.setExcerpt(request.getExcerpt());
        blog.setImage(request.getImage());
        blog.setAuthor(request.getAuthor());
        blog.setTags(
                request.getTags() != null
                        ? request.getTags()
                        : new ArrayList<>()
        );
        blog.setPublished(
                request.getPublished() != null
                        ? request.getPublished()
                        : false
        );

        blog.setViews(0);
        blog.setCreatedAt(OffsetDateTime.now());
        blog.setUpdatedAt(OffsetDateTime.now());

        if (blog.getPublished()) {
            blog.setPublishedAt(OffsetDateTime.now());
        }

        return blogRepository.save(blog);
    }

    // READ ALL
    public List<Blog> getAllBlogs() {

        return blogRepository.findAll();
    }

    // READ ONE
    public Blog getBlogById(Long id) {

        return blogRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Blog updateBlog(
            Long id,
            BlogRequest request
    ) {

        Blog blog = blogRepository.findById(id).orElse(null);

        if (blog == null) {
            return null;
        }

        blog.setTitle(request.getTitle());
        blog.setSlug(request.getSlug());
        blog.setContent(request.getContent());
        blog.setExcerpt(request.getExcerpt());
        blog.setImage(request.getImage());
        blog.setAuthor(request.getAuthor());
        blog.setTags(
                request.getTags() != null
                        ? request.getTags()
                        : new ArrayList<>()
        );
        blog.setPublished(
                request.getPublished() != null
                        ? request.getPublished()
                        : false
        );

        blog.setUpdatedAt(OffsetDateTime.now());

        if (blog.getPublished()) {
            if (blog.getPublishedAt() == null) {
                blog.setPublishedAt(OffsetDateTime.now());
            }
        } else {
            blog.setPublishedAt(null);
        }

        return blogRepository.save(blog);
    }

    // DELETE
    public boolean deleteBlog(Long id) {

        Blog blog = blogRepository.findById(id).orElse(null);

        if (blog == null) {
            return false;
        }

        blogRepository.delete(blog);

        return true;
    }
}