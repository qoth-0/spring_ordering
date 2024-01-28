package com.example.orderSystem.ordering.service;

import com.example.orderSystem.item.domain.Item;
import com.example.orderSystem.item.repository.ItemRepository;
import com.example.orderSystem.orderItem.domain.OrderItem;
import com.example.orderSystem.orderItem.repository.OrderItemRepository;
import com.example.orderSystem.ordering.domain.OrderStatus;
import com.example.orderSystem.ordering.domain.Ordering;
import com.example.orderSystem.ordering.dto.OrderingListResDto;
import com.example.orderSystem.ordering.dto.OrderingNewReqDto;
import com.example.orderSystem.ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderingService {

    private final OrderingRepository orderingRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderingService(OrderingRepository orderingRepository, ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.orderingRepository = orderingRepository;
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderingListResDto> findAll() {
        List<Ordering> orderings = orderingRepository.findAll();
        List<OrderingListResDto> orderingListResDtos = new ArrayList<>();
        for(Ordering a : orderings) {
            OrderingListResDto orderingListResDto = new OrderingListResDto();
            orderingListResDto.setId(a.getId());
            orderingListResDto.setMember(a.getMember());
            orderingListResDto.setOrderStatus(a.getOrderStatus());
            orderingListResDtos.add(orderingListResDto);
        }
        return orderingListResDtos;
    }

    public void orderNew(OrderingNewReqDto orderingNewReqDto) {
        Ordering ordering = Ordering.builder()
                .member(orderingNewReqDto.getMember())
                .orderStatus(OrderStatus.ORDERED)
                .build();
        orderingRepository.save(ordering);
        List<Long> items = orderingNewReqDto.getItemId();
        List<Long> count = orderingNewReqDto.getCount();
        for(int i=0; i<items.size(); i++) {
            Item item = itemRepository.findById(items.get(i)).orElseThrow(EntityNotFoundException::new);
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .quantity(count.get(i))
                    .ordering(ordering)
                    .build();
            item.stockUpdate(ordering.getOrderStatus(), count.get(i));
            orderItemRepository.save(orderItem);
        }
    }

    public void orderCancle(Long id) {
        Ordering ordering = orderingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ordering.setOrderStatus(OrderStatus.CANCELED);
        orderingRepository.save(ordering);
        List<OrderItem> orderItems = orderItemRepository.findByOrdering(ordering);
        for(OrderItem a : orderItems) {
            Item item = a.getItem();
            item.stockUpdate(ordering.getOrderStatus(), a.getQuantity());
            itemRepository.save(item);
        }
    }
}
