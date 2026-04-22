package put.io.patterns.implement;

public class CoolerObserver implements SystemStateObserver {
    @Override
    public void update(SystemMonitor monitor) {
        if (monitor.getLastSystemState().getCpuTemp() > 60.00) {
            System.out.println("> Increasing cooling of the CPU...");
        }
    }
}