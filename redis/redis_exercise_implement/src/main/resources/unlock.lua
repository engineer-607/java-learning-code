--获取id KEYS[1]是需要传入的参数
local id = redis.call('get',KEYS[1])
--ARGV[1]是传入的参数表示线程id
if(id == ARGV[1]) then
    --释放锁
    return redis.call('del',KEYS[1])
end
return 0