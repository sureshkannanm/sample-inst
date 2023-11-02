package com.aciworldwide.web.institution.service;

import com.aciworldwide.web.institution.entity.Institution;
import com.aciworldwide.web.institution.model.dto.InstitutionDTO;
import com.aciworldwide.web.institution.model.request.CreateInstitutionRequest;
import com.aciworldwide.web.institution.repository.InstitutionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.mutiny.Uni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstitutionService {

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private ObjectMapper mapper;

    public Uni<List<InstitutionDTO>> getAllInstitutions() {
        return institutionRepository.findAll().map(
            institutionList -> {
                List<InstitutionDTO> institutionDTOList = new ArrayList<>();
                institutionList.forEach(
                    institution -> institutionDTOList.add(mapper.convertValue(institution, InstitutionDTO.class))
                );
                return institutionDTOList;
            }
        );
    }

    public Uni<InstitutionDTO> getInstitutionById(Integer id) {
        return institutionRepository.findById(id).map(
            institution -> mapper.convertValue(institution, InstitutionDTO.class)
        );
    }

    public Uni<InstitutionDTO> createInstitution(CreateInstitutionRequest request) {
        Institution institution = mapper.convertValue(request, Institution.class);
        return institutionRepository.save(institution).map(
            ins -> mapper.convertValue(ins, InstitutionDTO.class)
        );
    }
}
