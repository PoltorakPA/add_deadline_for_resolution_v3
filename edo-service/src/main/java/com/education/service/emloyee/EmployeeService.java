package com.education.service.emloyee;

import model.dto.EmployeeDto;

import java.net.URISyntaxException;
import java.util.Collection;

/**
 * @author Kiladze George
 * интерфейс сервиса в edo-service
 */
public interface EmployeeService {

    void save(EmployeeDto employeeDto) throws URISyntaxException;

    void moveToArchived(Long id) throws URISyntaxException;

    EmployeeDto findById(Long id) throws URISyntaxException;

    Collection<EmployeeDto> findAll();

    Collection<EmployeeDto> findAllById(String ids);

    EmployeeDto findByIdAndArchivedDateNull(Long id);

    Collection<EmployeeDto> findByIdInAndArchivedDateNull(String ids);

    Collection<EmployeeDto> findAllByFullName(String fullName);

    Collection<EmployeeDto> saveCollection(Collection<EmployeeDto> employeeDto);

}
