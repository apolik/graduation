package org.polik.votingsystem.util.validation;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Polik on 4/17/2022
 */
public class NoHtmlValidator implements ConstraintValidator<NoHtml, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || Jsoup.isValid(value, Safelist.none());
    }
}
