from heapq import *
import math
import types
import random

class data_point:
    x=y=0.0
    def __init__(self,x,y,id):
        self.x=x
        self.y=y
        self.id=id

    def center_point(self):
        return (self.x,self.y)

    def __repr__(self):
        return "({" + str(self.id) + "} " + str(self.x) + "," + str(self.y) + ")"


class cluster:
    def add(self,item):
        self.items.append(item)

    def center_point(self):
        """return the centerpoint of the cluster"""
        tx=ty=0.0
        l=(float)(len(self.items))
        for i in self.items:
            cp=i.center_point()
            tx+=cp[0]
            ty+=cp[1]
        return (tx/l,ty/l)

    def __init__(self, p):
        self.items=[]
        if p is not None:
            if getattr(p, '__iter__', False):
                self.items.extend(p)
            else:
                self.items.append(p)

    def __repr__(self):
        return "[cp={"+str(self.center_point())+"} items="+' '.join([str(x) for x in self.items])+"]"

def distance(c1, c2):
    cp=c1.center_point()
    ocp=c2.center_point()
    return math.sqrt(math.pow((cp[0]-ocp[0]),2)+math.pow((cp[1]-ocp[1]),2))

def hierarchical_cluster(items):
    grouped_clusters=[]
    remaining_clusters=[cluster(x) for x in items]
    grouped_clusters.append([x for x in remaining_clusters])
    distance_q=[(distance(x,y),x,y) for x in remaining_clusters for y in remaining_clusters if not x==y]
    #heapify(distance_q)

    while len(distance_q) > 0:
        print("remaining: " + str(remaining_clusters) + "\n")
        #print("q" + str(distance_q) + "\n")
        vals=heappop(distance_q)[1:3]
        #print("picked: " + str(vals) + "\n")
        #print("q" + str(distance_q) + "\n")
# remove picked items from distance_q
        distance_q=[x for x in distance_q if x[1] not in vals and x[2] not in vals]
        #heapify(distance_q) # do i need this?
# remove picked items from remaining clusters
        remaining_clusters.remove(vals[0])
        remaining_clusters.remove(vals[1])
#create new cluster
        c=cluster(vals)
        td=[(distance(x,c),x,c) for x in remaining_clusters]
        remaining_clusters.append(c)
        for x in td:
            heappush(distance_q,x)
        grouped_clusters.append([x for x in remaining_clusters])

    return grouped_clusters


r=random.Random(0)
#data=[data_point(r.randint(0,100),r.randint(0,100)) for x in xrange(4)]
data=[data_point(2*x,0,x) for x in xrange(5)]
for x in hierarchical_cluster(data):
    print(x)
