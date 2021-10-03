package bank.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class GenericDaoImpl<T> extends AbstractDao<T> implements GenericDao<T> {
}
