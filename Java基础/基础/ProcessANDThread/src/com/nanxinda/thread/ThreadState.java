package com.nanxinda.thread;
/**
 * Java线程状态转换图
 *
 *
 *                         ①【新建】
 *                         ┌─────┐
 *                         │ NEW │
 *                         │     │
 *                         └──┬──┘
 *                            │ start()
 *                            ↓
 *    ┌─────────────────────────────────────────┐
 *    │               ②【可运行】Runnable        │
 *    │  ┌──────┐        调度        ┌──────┐   │
 *    │  │ READY│ ◄──────────────── │RUNNING│   │
 *    │  │就绪态│    时间片用完/      │运行态 │   │
 *    │  │     │ ────────────────► │      │   │
 *    │  └──────┘    获得CPU        └──────┘   │
 *    │                              │         │
 *    └──────────────────────────────┼─────────┘
 *                                   │
 *              ┌────────────────────┼────────────────────┐
 *              ↓                    ↓                    ↓
 *    【阻塞】          【无限等待】          【计时等待】
 *    ┌──────┐         ┌──────┐         ┌──────┐
 *    │BLOCKED│         │WAITING│         │TIMED_│
 *    │      │         │      │         │WAITING│
 *    └───┬──┘         └───┬──┘         └───┬──┘
 *        │                 │                 │
 *        └─────────────────┼─────────────────┘
 *                          │ 条件满足/超时/唤醒
 *                          ↓
 *                   ③【终止】
 *                    ┌─────┐
 *                    │TERMI-│
 *                    │NATED │
 *                    └─────┘
 *
 *
 * ──────────────────── 状态说明 ────────────────────
 *
 * 【NEW】        尚未启动的线程
 *
 * 【RUNNABLE】   在Java虚拟机中执行的线程
 *   ├─ READY     就绪，等待CPU调度
 *   └─ RUNNING   正在运行
 *
 * 【BLOCKED】    被阻塞等待监视器锁
 *
 * 【WAITING】    无限期等待另一个线程执行特定动作
 *
 * 【TIMED_WAITING】  等待另一个线程执行动作达到指定时间
 *
 * 【TERMINATED】  已退出的线程
 *
 *
 * ──────────────────── 转换触发条件 ────────────────────
 *
 * NEW → RUNNABLE
 *   Thread.start()
 *
 * RUNNABLE ↔ RUNNABLE(READY/RUNNING)
 *   线程调度：获得CPU / 时间片用完 / Thread.yield()
 *
 * RUNNABLE → WAITING
 *   Object.wait() / Thread.join() / LockSupport.park()
 *
 * WAITING → RUNNABLE
 *   Object.notify() / Object.notifyAll() / LockSupport.unpark()
 *
 * RUNNABLE → TIMED_WAITING
 *   Thread.sleep(time) / Object.wait(time) / Thread.join(time)
 *   LockSupport.parkNanos() / LockSupport.parkUntil()
 *
 * TIMED_WAITING → RUNNABLE
 *   等待时间结束 / 被唤醒(Object.notify/Object.notifyAll/LockSupport.unpark)
 *
 * RUNNABLE → BLOCKED
 *   等待进入同步代码块（未获得锁）
 *
 * BLOCKED → RUNNABLE
 *   获得监视器锁
 *
 * RUNNABLE → TERMINATED
 *   线程执行结束
 *   ------------------注意点---------------------
 *   由【BLOCKED】、【WAITING】、【TIMED_WAITING】到【RUNNABLE】
 *   不一定就进入RUNNING状态，处于RUNNING还是处于READY由系统内核决定
 */
public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
    /*
- NEW
  尚未启动的线程处于此状态。

- RUNNABLE
  在Java虚拟机中执行的线程处于此状态。

- BLOCKED
  被阻塞等待监视器锁定的线程处于此状态。

- WAITING
  正在等待另一个线程执行特定动作的线程处于此状态。

- TIMED_WAITING
  正在等待另一个线程执行动作达到指定等待时间的线程处于此状态。

- TERMINATED
  已退出的线程处于此状态。
         */
        State state = new State();
        System.out.println(state.getName()+"状态是"+state.getState());
        state.start();
        while (state.getState()!= Thread.State.TERMINATED){
            System.out.println(state.getName()+"状态是"+state.getState());
            Thread.sleep(50);
        }
        System.out.println(state.getName()+"状态是"+state.getState());
    }
}
class State extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("hi"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}