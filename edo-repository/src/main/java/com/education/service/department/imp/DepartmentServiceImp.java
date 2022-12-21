package com.education.service.department.imp;

import com.education.entity.Department;
import com.education.repository.DepartmentRepository;
import com.education.service.department.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 *@author Usolkin Dmitry
 * Сервис, который использует методы Repository
 * Сервис рабоатет с сущностью Department
 * Добавляет, архивирует
 * достает по id департамент,несколько департаментов и департаменты,
которые не имеют  дату архивации
 */

@Service
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentServiceImp(DepartmentRepository repository) {
        this.repository = repository;
    }

    /**
     * добавляет департамент
     * @param department
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Department department) {
        department.setCreationDate(ZonedDateTime.now());
        repository.save(department);
    }

    /**
     * заносит дату архивации, тем самым арххивируя департамент
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeToArchived(Long id) {
        Optional<Department> depar = repository.findById(id);
        if (!depar.isEmpty()) {
            Department department = depar.get();
            department.setArchivedDate(ZonedDateTime.now());
            repository.save(department);
        }
    }

    /**
     * достает департамент по id
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Department findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * достает департамент по нескольким id
     * @param ids
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Department> findByAllId(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    /**
     * достает департамент без даты архивации по id
     * @param id
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Department findByIdNotArchived(Long id) {
        return repository.findByIdAndArchivedDateNull(id);
    }

    /**
     * достает департаменты без даты архивации по нескольким id
     * @param ids
     * @return
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<Department> findByAllIdNotArchived(Iterable<Long> ids) {
        return repository.findByIdInAndArchivedDateNull(ids);
    }
}
