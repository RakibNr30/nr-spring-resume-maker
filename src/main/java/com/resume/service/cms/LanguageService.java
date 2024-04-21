package com.resume.service.cms;

import com.resume.dto.PageRequestDto;
import com.resume.entity.cms.Language;
import com.resume.helper.JpaSpecificationHelper;
import com.resume.repository.cms.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> findAll() {
        return this.languageRepository.findAll();
    }

    public Page<Language> findPaginated(PageRequestDto pageRequestDto) {
        Specification<Language> specification = JpaSpecificationHelper.searchQuery(pageRequestDto.getSearch());
        return this.languageRepository.findAll(specification, pageRequestDto.getPageable());
    }

    public Language save(Language language) {
        return this.languageRepository.save(language);
    }

    public Language findById(Long id) {
        return this.languageRepository.findById(id).orElse(null);
    }

    public Language update(Language language) {
        return this.languageRepository.save(language);
    }

    public void delete(Language language) {
        this.languageRepository.delete(language);
    }
}
