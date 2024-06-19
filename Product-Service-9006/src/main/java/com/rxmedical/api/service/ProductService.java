package com.rxmedical.api.service;

import java.util.List;
import java.util.Optional;

import com.rxmedical.api.client.UserServiceClient;
import com.rxmedical.api.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rxmedical.api.model.po.Product;
import com.rxmedical.api.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserServiceClient userServiceClient;
//    @Autowired
//    private HistoryRepository historyRepository;


    /**
     * [搜索-前台] 取得所有產品資訊
     * @return List 商品列表
     */
    public List<ShowProductsDto> getProductList() {
        return findAllProducts().stream()
            .map(product -> new ShowProductsDto(
                    product.getId(),
                    product.getName(),
                    product.getStock(),
                    product.getCategory(),
                    product.getPicture())
            )
            .toList();
    }

    /**
     * [搜索-前台] 前台取得單一商品的資訊
     * @param productId 商品id
     * @return ProductItemInfoDto 商品資料
     */
    public ProductItemInfoDto getProductItemInfo(Integer productId) {
        Product product = findProductById(productId);
        if (product != null) {
            return new ProductItemInfoDto(
                    product.getId(),
                    product.getName(),
                    product.getCategory(),
                    product.getStock(),
                    product.getDescription(),
                    product.getPicture()
            );
        }
        return null;
    }

    /**
     * [搜索-後台] 取得所有產品資訊
     * @return List 商品列表
     */
    public List<ShowMaterialsDto> getMaterialList() {
        return findAllProducts().stream()
                .map(product -> new ShowMaterialsDto(
                        product.getId(),
                        product.getCode(),
                        product.getName(),
                        product.getStock(),
                        product.getStorage(),
                        product.getCategory())
                )
                .toList();
    }

    /**
     * [搜索] 取得單一產品的詳細資訊
     * @return MaterialInfoDto 單一商品詳細資料
     */
    public MaterialInfoDto getMaterialInfo(Integer id) {
        Product product = findProductById(id);
        if (product != null) {
            return new MaterialInfoDto(
                    product.getId(),
                    product.getCode(),
                    product.getName(),
                    product.getCategory(),
                    product.getSafetyThreshold(),
                    product.getStorage(),
                    product.getDescription(),
                    product.getPicture());
        }
        return null;
    }

    /**
     * [更新] 更新後台產品的資料
     * @param infoDto 產品要更新的資訊
     * @return Boolean 是否成功
     */
    public Boolean updateMaterialInfo(MaterialUpdateInfoDto infoDto) {
        Product product = findProductById(infoDto.getProductId());
        if (product != null) {
            product.setName(infoDto.getName());
            product.setCategory(infoDto.getCategory());
            product.setSafetyThreshold(infoDto.getSafetyThreshold());
            product.setStorage(infoDto.getStorage());
            product.setDescription(infoDto.getDescription());

            if (infoDto.getUpdatePicture() != null) {
                product.setPicture(infoDto.getUpdatePicture());
            }

//            productRepository.save(product);
            saveProduct(product);
            return true;
        }
        return false;
    }

//    /**
//     * [新增] 註冊產品資訊
//     * @param infoDto 產品資訊及第一筆進貨資料
//     * @return Boolean 是否成功
//     */
//    @Transactional
//    public Boolean registerProduct(MaterialFileUploadDto infoDto) {
//
//        // 因為經過aop，所以直接get
//        User user = userService.findUserById(infoDto.getUserId());
//
//        // 產品資料寫入資料庫
//        Product product = new Product();
//        product.setCode(infoDto.getCode());
//        product.setName(infoDto.getName());
//        product.setStock(0);
//        product.setSafetyThreshold(infoDto.getSafetyThreshold());
//        product.setDescription(infoDto.getDescription());
//        product.setStorage(infoDto.getStorage());
//        product.setPicture(infoDto.getPicture());
//        product.setCategory(infoDto.getCategory());
//        Product result = productRepository.save(product);
//
//        // 產品資料寫入歷史紀錄
//        History history = new History();
//        history.setQuantity(infoDto.getQuantity());
//        history.setPrice(infoDto.getPrice());
//        history.setFlow("進");
//        history.setProduct(result);
//        history.setRecord(null);
//        history.setUser(user);
//        historyRepository.save(history);
//
//        // 加入第一筆產品庫存
//        result.setStock(infoDto.getQuantity());
//        productRepository.save(result);
//        return true;
//    }

    public Product findProductById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

}
