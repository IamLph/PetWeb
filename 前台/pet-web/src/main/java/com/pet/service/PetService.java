package com.pet.service;

import com.pet.common.PageResult;
import com.pet.common.Result;
import com.pet.pojo.Pet;
import com.pet.pojo.PetUser;

public interface PetService {
    Result getAllPets();

    Result insertSharePet(PetUser petUser);

    Result getSharePets();

    Result getPet(String id);

    PageResult<Result<Object>> getPagePet(Integer currentPage, Integer pageSize);

    Result addPet(Pet pet);

    Result updatePet(Pet pet);

    Result deletePet(String id);

    Result updateSharePet(PetUser petUser);

    Result deleteSharePet(PetUser petUser);
}
