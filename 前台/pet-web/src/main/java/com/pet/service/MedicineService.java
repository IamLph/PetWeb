package com.pet.service;

import com.pet.common.Result;
import com.pet.pojo.Medicine;

public interface MedicineService {
    Result getAllMedicines();

    Result addMedicine(Medicine medicine);

    Result updateMedicine(Medicine medicine);

    Result deleteMedicine(String id);
}
