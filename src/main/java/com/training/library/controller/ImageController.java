package com.training.library.controller;

import com.training.library.model.Image;
import com.training.library.model.Order;
import com.training.library.repository.ImageRepository;
import com.training.library.service.OrderService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageRepository imageRepository;
    private final OrderService orderService;

    public ImageController(ImageRepository imageRepository, OrderService orderService) {
        this.imageRepository = imageRepository;
        this.orderService = orderService;
    }

    @PostMapping("{id}")
    ResponseEntity<Long> uploadImage(@PathVariable("id") Long orderId, @RequestParam MultipartFile multipartFile) throws IOException {
        Image dbImage = new Image();
        dbImage.setName(multipartFile.getOriginalFilename());
        dbImage.setPhoto(multipartFile.getBytes());

        final Long id = imageRepository.save(dbImage).getId();
        Order order = orderService.getById(orderId);
        order.setImageId(id);
        orderService.update(order);

        return ResponseEntity.ok(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
        final Image image = imageRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();
        String mimeType = fileTypeMap.getContentType(image.getName());


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(new ByteArrayResource(image.getPhoto()));
    }
}
