/*package com.raghav.ecommerce.service.impl;

import com.raghav.ecommerce.model.HomeCategory;
import com.raghav.ecommerce.repository.HomeCategoryRepository;
import com.raghav.ecommerce.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {

    private final HomeCategoryRepository homeCategoryRepository;


    @Override
    public HomeCategory createCategory(HomeCategory categories) {

        return homeCategoryRepository.save(categories);

    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> categories) {
        if (homeCategoryRepository.findAll().isEmpty()) {
            return homeCategoryRepository.saveAll(categories);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    public List<HomeCategory> getAllCategories() {
        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateCategory(HomeCategory category, Long id) throws Exception {
        HomeCategory existingCategory = homeCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));
        if(category.getImage()!=null){
            existingCategory.setImage(category.getImage());
        }
        if(category.getCategoryId()!=null){
            existingCategory.setCategoryId(category.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategory);
    }


}
*/
package com.raghav.ecommerce.service.impl;

import com.raghav.ecommerce.model.HomeCategory;
import com.raghav.ecommerce.repository.HomeCategoryRepository;
import com.raghav.ecommerce.service.HomeCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeCategoryServiceImpl implements HomeCategoryService {

    private final HomeCategoryRepository homeCategoryRepository;


    @Override
    public HomeCategory createCategory(HomeCategory categories) {

        return homeCategoryRepository.save(categories);

    }

    @Override
    public synchronized List<HomeCategory> createCategories(List<HomeCategory> categories) {
        // Keys of categories that are already saved (section + categoryId)
        Set<String> existingKeys = homeCategoryRepository.findAll().stream()
                .map(c -> c.getSection() + "|" + c.getCategoryId())
                .collect(Collectors.toSet());

        // Save only the categories that are not in the database yet
        List<HomeCategory> newOnes = categories.stream()
                .filter(c -> !existingKeys.contains(c.getSection() + "|" + c.getCategoryId()))
                .collect(Collectors.toList());

        if (!newOnes.isEmpty()) {
            homeCategoryRepository.saveAll(newOnes);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    public List<HomeCategory> getAllCategories() {
        return homeCategoryRepository.findAll();
    }

    @Override
    public HomeCategory updateCategory(HomeCategory category, Long id) throws Exception {
        HomeCategory existingCategory = homeCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));
        if(category.getImage()!=null){
            existingCategory.setImage(category.getImage());
        }
        if(category.getCategoryId()!=null){
            existingCategory.setCategoryId(category.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategory);
    }


}