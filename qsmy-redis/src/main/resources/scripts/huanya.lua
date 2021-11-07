local result = redis.call('smembers', KEYS[1])
local result1 = {}
for i = 1, #result do
table.insert(result1, result[i])
end
table.insert(result1, 5)
return result1