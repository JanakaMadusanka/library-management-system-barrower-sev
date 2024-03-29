package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.BorrowerDto;
import org.example.dto.LoginDto;
import org.example.entity.BorrowerEntity;
import org.example.service.BorrowerService;
import org.example.service.LoginService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrower")
@RequiredArgsConstructor
@CrossOrigin
public class BorrowerController {

    final BorrowerService service;
    final LoginService loginService;
    @PostMapping("/add")
    public void addBorrower(@RequestBody BorrowerDto borrowerDto){
        service.addBorrower(borrowerDto);
        loginService.insertLoginData(new LoginDto(borrowerDto.getEmail(),borrowerDto.getPassword()));
    }
    @GetMapping("/get")
    public Iterable<BorrowerEntity> getBorrower(){
        return service.getBorrowers();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteBorrower(@PathVariable Long id){
        service.deleteBorrower(id);
        return "Deleted";
    }
    @GetMapping("/search/{userName}")
    public BorrowerDto getBorrowerByUserName(@PathVariable String userName){
        return service.getBorrowerByUserName(userName);
    }
    @GetMapping("/isExist/{userName}")
    public boolean isExistUser(@PathVariable String userName){
        return service.isExistUser(userName);
    }
}
