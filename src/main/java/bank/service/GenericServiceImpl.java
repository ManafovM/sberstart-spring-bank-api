package bank.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class GenericServiceImpl<T> extends AbstractService<T> implements GenericService<T> {
}
