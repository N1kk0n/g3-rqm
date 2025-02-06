package g3.rqm.requestmanager.restapi.dtos.requests;

import g3.rqm.requestmanager.restapi.dtos.requests.listitems.QueueInfoSorting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class QueueInfoBody {
    private int page_number;
    private int page_size;
    private QueueInfoFilter filter;
    private List<QueueInfoSorting> sorting;
}
