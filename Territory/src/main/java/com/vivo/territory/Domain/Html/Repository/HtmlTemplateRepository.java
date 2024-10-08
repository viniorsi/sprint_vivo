package com.vivo.territory.Domain.Html.Repository;

import com.vivo.territory.Domain.Html.Domain.HtmlTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HtmlTemplateRepository extends JpaRepository<HtmlTemplate, Long> {
}
