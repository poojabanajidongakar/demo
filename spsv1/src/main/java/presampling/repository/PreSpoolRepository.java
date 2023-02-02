package presampling.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;


@Repository
public interface PreSpoolRepository {
	Cheque findById(String policy,User u);
}
