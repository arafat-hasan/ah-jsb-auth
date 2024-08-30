package com.hingtingchot.ah_jsb_auth.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;

import com.hingtingchot.ah_jsb_auth.service.AddressService;


/**
 * Validate that the label value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = AddressLabelUnique.AddressLabelUniqueValidator.class
)
public @interface AddressLabelUnique {

    String message() default "{Exists.address.label}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class AddressLabelUniqueValidator implements ConstraintValidator<AddressLabelUnique, String> {

        private final AddressService addressService;
        private final HttpServletRequest request;

        public AddressLabelUniqueValidator(final AddressService addressService,
                final HttpServletRequest request) {
            this.addressService = addressService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(addressService.get(Long.parseLong(currentId)).getLabel())) {
                // value hasn't changed
                return true;
            }
            return !addressService.labelExists(value);
        }

    }

}
