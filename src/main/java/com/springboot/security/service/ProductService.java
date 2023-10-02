package com.springboot.security.service;

import com.springboot.security.dto.Product;
import com.springboot.security.entity.UserInfo;
import com.springboot.security.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList=null;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @PostConstruct
    public void loadProducts(){
        productList= IntStream.rangeClosed(1,100)
                .mapToObj(i->Product.builder()
                .productId(i)
                .productName("product "+i)
                .quantity(new Random().nextInt(10))
                .price(new Random().nextInt(5000)).build()).collect(Collectors.toList());
    }

    public List<Product> getProducts(){

        return productList;
    }

    public Product getProduct(int id){
        return productList.stream().filter(p->p.getProductId()==id)
                .findAny()
                .orElseThrow(()->new RuntimeException("product "+id+" not found"));
    }
    public String addUser(UserInfo userInfo){

        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);

        return "saved successfully";
    }
}
