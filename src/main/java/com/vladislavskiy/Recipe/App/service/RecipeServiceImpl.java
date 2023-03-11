package com.vladislavskiy.Recipe.App.service;

import com.vladislavskiy.Recipe.App.dto.ReceiptDto;
import com.vladislavskiy.Recipe.App.dto.UserDto;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.ReceiptRepository;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService{
    @Autowired
    private ReceiptRepository repository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Recept getReceiptById(final Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Recept> findAll() {
       return repository.findAll();
    }
    @Override
    public void addReceiptForCurrentUser(ReceiptDto receiptDto)
    {
        Recept recept = new Recept();
        User user = userRepository.getById(receiptDto.getUserId());
        if(user!= null) {
            recept.setName(receiptDto.getName());
            recept.setDescription(receiptDto.getDescription());
            recept.setUser(user);
            repository.save(recept);
        }
        else
            throw new NullPointerException("RECEIPT CAN'T BE ADDED, BECAUSE USER DOESN'T EXIT BY CURRENT ID: " + receiptDto.getUserId());
    }

    @Override
    public List<Recept> findAllByUserId(Integer id) {
        return repository.findAllByUser_Id(id);
    }
}
