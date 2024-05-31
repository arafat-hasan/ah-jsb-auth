package com.hingtingchot.ah_auth.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.hingtingchot.ah_auth.service.SysAdminService;
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


/**
 * Validate that the email value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = SysAdminEmailUnique.SysAdminEmailUniqueValidator.class
)
public @interface SysAdminEmailUnique {

    String message() default "{Exists.sysAdmin.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class SysAdminEmailUniqueValidator implements ConstraintValidator<SysAdminEmailUnique, String> {

        private final SysAdminService sysAdminService;
        private final HttpServletRequest request;

        public SysAdminEmailUniqueValidator(final SysAdminService sysAdminService,
                final HttpServletRequest request) {
            this.sysAdminService = sysAdminService;
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
            if (currentId != null && value.equalsIgnoreCase(sysAdminService.get(Long.parseLong(currentId)).getEmail())) {
                // value hasn't changed
                return true;
            }
            return !sysAdminService.emailExists(value);
        }

    }

}
