s = 50
weight =[10,120,100,10,60]
cost = [5,30,5,20,10]
items = {(10,5),(120,30),(100,5),(10,20),(60,10)}
m = [50][50]
for j in range(0,s):
    m[0][j] = 0;

for i in range(1,5):
    for j in range(0,s):
        if weight[i] > j:
            m[i][j] = m[i-1][j]
        else:
            m[i,j] = max(m[i-1][j], m[i-1][j-weight[i]]+cost[i])

reconstruction = []
i = len(weight)
j = s
while i>0:
    if m[i][j] != m[i-1][j]:
        reconstruction.append(items[i-1])
        j-=items[i-1][1]
    i -= 1

reconstruction.reverse()
for value, weight in reconstruction:
    print(value, " ", weight)
