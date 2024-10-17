package com.vivo.territory.Controller;

import com.vivo.territory.Domain.Products.DTO.ProductsDTO;
import com.vivo.territory.Domain.User.DTO.DTOUserRegister;
import com.vivo.territory.Domain.User.Entity.User;
import com.vivo.territory.Domain.UserProduct.DTO.DTOUserProduct;
import com.vivo.territory.Domain.UserProduct.DTO.DTOUserProductDetails;
import com.vivo.territory.Domain.UserVerification.DTO.DTOUserVerificationStatus;
import com.vivo.territory.Domain.UserVerification.DTO.DTOUserVerificationStatusRequest;
import com.vivo.territory.Service.User.UserRegisterService;
import com.vivo.territory.Service.User.UserVerificationCodeService;
import com.vivo.territory.Service.UserProduct.UserProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRegisterService userRegisterService;

    @Autowired
    private UserVerificationCodeService userVerificationCodeService;

    @Autowired
    private UserProductService userProductService;

    @PostMapping("/register")
    @Transactional
    public ResponseEntity createUser(@RequestBody @Valid DTOUserRegister dtoUserRegister, UriComponentsBuilder uriBuilder) {
        try {
            var user = new User(dtoUserRegister);
            var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
            var userDetails = userRegisterService.createUser(dtoUserRegister);
            return ResponseEntity.created(uri).body(userDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/codeVerification")
    @Transactional
    public ResponseEntity codeVerifications(@RequestBody @Valid DTOUserVerificationStatusRequest dtoUserVerificationStatusRequest) {
        try {
            var user = userVerificationCodeService.codeVerification(dtoUserVerificationStatusRequest.cpf(), dtoUserVerificationStatusRequest.verificationCode());
            if (user != null) {
                return ResponseEntity.ok(new DTOUserVerificationStatus(user));
            }
            return ResponseEntity.badRequest().body("Invalid code");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/getProduct")
    @Transactional
    public ResponseEntity getProduct(@RequestBody @Valid DTOUserProduct dtoUserProduct) {
        try {
            var userDetails = userProductService.addProductToUser(dtoUserProduct);
            return ResponseEntity.ok(userDetails);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("{user_id}/userProducts")
    public ResponseEntity<Page<DTOUserProductDetails>> listUserProducts( @PathVariable("user_id") Long userId, @PageableDefault(size = 10, page = 0,sort = {"id"}) Pageable paginacao) throws Exception {

        var page = userProductService.listUserProducts(userId,paginacao);
        return ResponseEntity.ok(page);

    }




}
