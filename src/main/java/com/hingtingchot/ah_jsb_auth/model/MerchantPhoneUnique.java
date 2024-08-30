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

import com.hingtingchot.ah_jsb_auth.service.MerchantService;


/**
 * Validate that the phone value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = MerchantPhoneUnique.MerchantPhoneUniqueValidator.class
)
public @interface MerchantPhoneUnique {

    String message() default "{Exists.merchant.phone}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class MerchantPhoneUniqueValidator implements ConstraintValidator<MerchantPhoneUnique, String> {

        private final MerchantService merchantService;
        private final HttpServletRequest request;

        public MerchantPhoneUniqueValidator(final MerchantService merchantService,
                final HttpServletRequest request) {
            this.merchantService = merchantService;
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
            if (currentId != null && value.equalsIgnoreCase(merchantService.get(Long.parseLong(currentId)).getPhone())) {
                // value hasn't changed
                return true;
            }
            return !merchantService.phoneExists(value);
        }

    }

}
