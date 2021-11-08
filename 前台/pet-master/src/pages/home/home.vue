<template>
    <div>
        <div>
            <HomeBbs></HomeBbs>
        </div>
    </div>
</template>

<script>
import HomeBbs from '../home/home-components/home-bbs.vue'
import HomeColumn from '../home/home-components/home-column.vue'

export default {
    components: {
        HomeColumn,
        HomeBbs,
    }
}
</script>
