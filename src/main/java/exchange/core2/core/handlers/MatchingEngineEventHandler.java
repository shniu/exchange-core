package exchange.core2.core.handlers;

import com.lmax.disruptor.EventHandler;
import exchange.core2.core.common.cmd.OrderCommand;
import exchange.core2.core.processors.MatchingEngineRouter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MatchingEngineEventHandler implements EventHandler<OrderCommand> {
    private MatchingEngineRouter matchingEngineRouter;

    public MatchingEngineEventHandler(MatchingEngineRouter matchingEngineRouter) {
        this.matchingEngineRouter = matchingEngineRouter;
    }

    @Override
    public void onEvent(OrderCommand cmd, long seq, boolean endOfBatch) throws Exception {
        log.info("-> MatchingEngineRouter 开始处理 cmd: {}, seq: {}", cmd, seq);
        matchingEngineRouter.processOrder(seq, cmd);
    }
}
