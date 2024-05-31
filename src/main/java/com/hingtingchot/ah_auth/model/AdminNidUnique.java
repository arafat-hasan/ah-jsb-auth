package com.hingtingchot.ah_auth.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import com.hingtingchot.ah_auth.service.AdminService;
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
 * Validate that the nid value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = AdminNidUnique.AdminNidUniqueValidator.class
)
public @interface AdminNidUnique {

    String message() default "{Exists.admin.nid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class AdminNidUniqueValidator implements ConstraintValidator<AdminNidUnique, String> {

        private final AdminService adminService;
        private final HttpServletRequest request;

        public AdminNidUniqueValidator(final AdminService adminService,
                final HttpServletRequest request) {
            this.adminService = adminService;
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
            if (currentId != null && value.equalsIgnoreCase(adminService.get(Long.parseLong(currentId)).getNid())) {
                // value hasn't changed
                return true;
            }
            return !adminService.nidExists(value);
        }

    }

}
