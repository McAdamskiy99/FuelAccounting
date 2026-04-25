package fuel.newaccounting.service;

import fuel.newaccounting.repository.ModelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ModelService {

    private final ModelRepository modelRepository;




}
