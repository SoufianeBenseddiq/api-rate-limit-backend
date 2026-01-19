package ma.ensa.backend.service.impl;

import org.springframework.transaction.annotation.Transactional;
import ma.ensa.backend.bean.ApiConsumer;
import ma.ensa.backend.dao.ApiConsumerRepository;
import ma.ensa.backend.service.facade.ApiConsumerService;
import ma.ensa.backend.utils.enums.ConsumerStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class ApiConsumerServiceImpl implements ApiConsumerService {

}