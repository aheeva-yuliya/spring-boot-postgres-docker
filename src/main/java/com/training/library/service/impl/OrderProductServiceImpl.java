package com.training.library.service.impl;

import com.training.library.converter.OrderProductConverter;
import com.training.library.converter.OrderToSubmitOrderDtoConverter;
import com.training.library.dto.OrderProductDto;
import com.training.library.dto.SubmitOrderDto;
import com.training.library.model.*;
import com.training.library.repository.ImageRepository;
import com.training.library.repository.OrderProductRepository;
import com.training.library.service.EmailSenderService;
import com.training.library.service.OrderProductService;
import com.training.library.service.OrderService;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final OrderProductConverter orderProductConverter;

    private final OrderService orderService;
    private final OrderToSubmitOrderDtoConverter orderToSubmitOrderDtoConverter;

    private final ImageRepository imageRepository;

    private final EmailSenderService emailSenderService;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository,
                                   OrderProductConverter orderProductConverter,
                                   OrderService orderService,
                                   OrderToSubmitOrderDtoConverter orderToSubmitOrderDtoConverter,
                                   ImageRepository imageRepository,
                                   EmailSenderService emailSenderService) {
        this.orderProductRepository = orderProductRepository;
        this.orderProductConverter = orderProductConverter;
        this.orderService = orderService;
        this.orderToSubmitOrderDtoConverter = orderToSubmitOrderDtoConverter;
        this.imageRepository = imageRepository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public OrderProduct save(OrderProductDto orderProductDto) {
        OrderProduct orderProduct = orderProductConverter.fromDto(orderProductDto);
        Order order = orderProduct.getOrderId();
        Double totalPrice = order.getTotalPrice() + orderProduct.getProductId().getPrice();
        order.setTotalPrice(totalPrice);
        orderService.update(order);
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public SubmitOrderDto get(long orderId) {
        Order order = orderService.getById(orderId);
        List<Product> chosenProducts = orderProductRepository.findProductByOrderId(orderId);
        final Image image = imageRepository.findById(order.getImageId()).orElseThrow(EntityNotFoundException::new);
        try {
            emailSenderService.sendEmail(createOrderEmail(order, chosenProducts), image);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return orderToSubmitOrderDtoConverter.convert(order, chosenProducts);
    }

    private Mail createOrderEmail(Order order, List<Product> list) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("name", order.getUserId().getName() + "!");
        properties.put("totalPrice", order.getTotalPrice());
        properties.put("list", list);

        return Mail.builder()
                .from("testmailappwise@gmail.com")
                .to(order.getUserId().getName())
                .htmlTemplate(new Mail.HtmlTemplate("sample", properties))
                .subject("This is sample email with spring boot and thymeleaf")
                .build();
    }
}
