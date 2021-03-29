package sort

func HeapSort(values []int) {
	buildHeap(values)
	for i := len(values) - 1; i > 0; i-- {
		values[0], values[i] = values[i], values[0]
		adjustHeap(values[:i], 0)
	}
}

func buildHeap(values []int) {
	for i := len(values); i >= 0; i-- {
		adjustHeap(values, i)
	}
}

func adjustHeap(values []int, pos int) {
	node := pos
	length := len(values)
	for node < length {
		child := 0
		if node < length/2-1 {
			if values[2*node+1] > values[2*node+2] {
				child = 2*node + 1
			} else {
				child = 2*node + 2
			}
		} else if node < (length-1)/2 {
			child = 2*node + 1
		}
		if child > 0 && values[child] > values[node] {
			values[node], values[child] = values[child], values[node]
			node = child
		} else {
			break
		}
	}
}
