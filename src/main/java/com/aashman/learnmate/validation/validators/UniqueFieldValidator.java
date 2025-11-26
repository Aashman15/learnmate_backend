package com.aashman.learnmate.validation.validators;
import com.aashman.learnmate.validation.annotations.UniqueField;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, List<?>> {

    private String fieldName;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) return true;

        Set<Object> seen = new HashSet<>();

        for (Object item : value) {
            try {
                Field field = item.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object fieldValue = field.get(item);

                if (fieldValue != null && !seen.add(fieldValue)) {
                    return false; // duplicate found
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException("Invalid field name for @UniqueField", e);
            }
        }

        return true;
    }
}
