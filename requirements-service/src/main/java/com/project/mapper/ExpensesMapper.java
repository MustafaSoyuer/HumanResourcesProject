package com.project.mapper;


import com.project.dto.response.AddExpensesResponseDto;
import com.project.dto.response.ExpensesListResponseDto;
import com.project.entity.Expenses;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpensesMapper {

    ExpensesMapper INSTANCE = Mappers.getMapper(ExpensesMapper.class);


    AddExpensesResponseDto fromExpensesToAddExpensesResponseDto(final Expenses expense);

    ExpensesListResponseDto fromExpensesToExpensesListResponseDto(final Expenses expenses);
}
