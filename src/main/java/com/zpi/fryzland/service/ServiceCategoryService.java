package com.zpi.fryzland.service;

import com.zpi.fryzland.model.EmployeeQualificationModel;
import com.zpi.fryzland.model.ServiceCategoryModel;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.repository.ServiceCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceCategoryService {

    private final ServiceCategoryRepository categoryRepository;

    public ServiceCategoryModel addCategory(ServiceCategoryModel categoryModel){
        return categoryRepository.save(categoryModel);
    }

    public Optional<ServiceCategoryModel> getCategoryById(int id){
        return categoryRepository.findById(id);
    }

    public List<ServiceCategoryModel> getAllCategories(){
        List<ServiceCategoryModel> listOfCategories = new ArrayList<>();
        for(ServiceCategoryModel categoryModel: categoryRepository.findAll()){
            listOfCategories.add(categoryModel);
        }
        return listOfCategories;
    }

    public List<ServiceCategoryModel> getAllUniqueCategoriesByEmployeeQualification(List<EmployeeQualificationModel> listOfQualifications) {
        return listOfQualifications.stream()
                .map(model -> model.getServiceCategoryModel())
                .distinct()
                .toList();
    }

    public void deleteCategory(ServiceCategoryModel categoryModel){
        categoryRepository.delete(categoryModel);
    }

    public void deleteCategoryById(int id){
        categoryRepository.deleteById(id);
    }
}
