package lk.ijse.gdse.hello_shoes.service.impl;

import lk.ijse.gdse.hello_shoes.dao.SupplierRepo;
import lk.ijse.gdse.hello_shoes.dto.SupplierDTO;
import lk.ijse.gdse.hello_shoes.entity.Supplier;
import lk.ijse.gdse.hello_shoes.service.SupplierService;
import lk.ijse.gdse.hello_shoes.util.Mapping;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class SupplierServiceIMPL implements SupplierService {
    private final SupplierRepo supplierRepo;
    private final Mapping mapping;
    @Override
    public SupplierDTO saveSupplier(SupplierDTO supplierDTO) {
        supplierDTO.setSup_code(UUID.randomUUID().toString());
        return mapping.toSupplierDto(supplierRepo.save(mapping.toSupplierEntity(supplierDTO)));
    }

    @Override
    public boolean updateSupplier(String id, SupplierDTO supplierDTO) {
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if (supplier.isPresent()){
            supplier.get().setSup_code(supplierDTO.getSup_code());
            supplier.get().setSup_name(supplierDTO.getSup_name());
            supplier.get().setCategory(supplierDTO.getCategory());
            supplier.get().setAddress_line_01(supplierDTO.getAddress_line_01());
            supplier.get().setAddress_line_02(supplierDTO.getAddress_line_02());
            supplier.get().setAddress_line_03(supplierDTO.getAddress_line_03());
            supplier.get().setAddress_line_04(supplierDTO.getAddress_line_04());
            supplier.get().setAddress_line_05(supplierDTO.getAddress_line_05());
            supplier.get().setAddress_line_06(supplierDTO.getAddress_line_06());
            supplier.get().setContact_no_01(supplierDTO.getContact_no_01());
            supplier.get().setContact_no_02(supplierDTO.getContact_no_02());
            supplier.get().setEmail(supplierDTO.getEmail());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteSupplier(String id) {
        supplierRepo.deleteById(id);
        return true;
    }

    @Override
    public List<SupplierDTO> getAllSupplier() {
        return mapping.toSupplierList(supplierRepo.findAll());
    }
}