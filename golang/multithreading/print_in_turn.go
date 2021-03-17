package main

import (
	"fmt"
	"sync"
)

var Limit = 100

func main() {
	wg := &sync.WaitGroup{}
	wg.Add(2)
	p := make(chan int)
	go G1(p, wg)
	go G2(p, wg)
	wg.Wait()
}

func G1(p chan int, wg *sync.WaitGroup) {
	for i := 1; i <= Limit; i++ {
		if i%2 == 1 {
			fmt.Printf("G1: %d\n", i)
		}
		p <- i
	}
	wg.Done()
}

func G2(p chan int, wg *sync.WaitGroup) {
	for i := 1; i <= Limit; i++ {
		if i%2 == 0 {
			fmt.Printf("G2: %d\n", i)
		}
		<-p
	}
	wg.Done()
}
