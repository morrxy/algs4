
n1 = 268435456
t1 = 1151.478

n2 = 134217728
t2 = 416.668

n3 = 67108864
t3 = 150.749

td1 = Math.log2(t1) - Math.log2(t2)
nd1 = Math.log2(n1) - Math.log2(n2)

td2 = Math.log2(t2) - Math.log2(t3)
nd2 = Math.log2(n2) - Math.log2(n3)

puts td1/nd1
puts td2/nd2