package com.vivo.territory.Service.html;

import com.vivo.territory.Domain.Html.Domain.HtmlTemplate;
import com.vivo.territory.Domain.Html.Repository.HtmlTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HtmlTemplateService {

    @Autowired
    private HtmlTemplateRepository htmlTemplateRepository;

    public String getTemplateContent(Long id) {
        HtmlTemplate template = htmlTemplateRepository.findById(id).orElse(null);
        if (template != null) {
            return template.getContent();
        } else {
            throw new RuntimeException("Template not found");
        }
    }

}
