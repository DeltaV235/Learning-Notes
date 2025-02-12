import {defineStore} from 'pinia'
import {computed, ref} from "vue";
/*
export const usemoneystore = definestore('money', {
    state: () => ({
        amount: 10000,
    }),
    getters: {
        amountinrmb: (state) => math.round(state.amount),
        amountinusd: (state) => math.round(state.amount * 0.14),
        amountinjpy: (state) => math.round(state.amount * 20.99),
    },
    actions: {
        buy(arg) {
            this.amount -= arg
        },
        topup(arg) {
            this.amount += arg
        },
    },
});
*/

export const useMoneyStore = defineStore('money', () => {
    const amount = ref(10000);

    const amountInRmb = computed(() => Math.round(amount.value));
    const amountInUsd = computed(() => Math.round(amount.value * 0.14));
    const amountInJpy = computed(() => Math.round(amount.value * 20.99));

    const buy = (arg) => {
        amount.value -= arg
    }
    const topUp = (arg) => {
        amount.value += arg
    }

    return {amount, amountInRmb, amountInUsd, amountInJpy, buy, topUp}
})
